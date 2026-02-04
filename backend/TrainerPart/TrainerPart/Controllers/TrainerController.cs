using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TrainerPart.DTO_s;
using TrainerPart.Models;

namespace TrainerPart.Controllers
{
    [ApiController]
    [Route("api/trainer")]
    public class TrainerController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public TrainerController(TrainerDbContext context)
        {
            _context = context;
        }

        [HttpGet("profile/{uid}")]
        public IActionResult GetProfileByTrainerId(int uid)
        {
            var trainerProfile = (
                from t in _context.Trainers
                join u in _context.Users on t.Uid equals u.Uid
                where t.Uid == uid
                select new TrainerProfileDto
                {
                    Tid = t.Tid,
                    Experience = t.Experience,
                    Salary = t.Salary,
                    Uname = u.Uname,
                    Password = u.Password,
                    Fname = u.Fname,
                    Lname = u.Lname,
                    Email = u.Email,
                    Contact = u.Contact,
                    Gender = u.Gender
                }
            ).FirstOrDefault();


            if (trainerProfile == null)
                return NotFound("Trainer not found");

            return Ok(trainerProfile);
        }


        //[HttpPut("profile/update/{trainerId}")]
        //public IActionResult UpdateTrainerProfile(int trainerId,[FromBody] UpdateTrainerProfileDtocs dto)
        //{
        //    var trainer = _context.Trainers
        //        .Include(t => t.User)   // ✅ correct navigation
        //        .FirstOrDefault(t => t.Tid == trainerId);

        //    if (trainer == null)
        //        return NotFound("Trainer not found");

        //    if (trainer.User == null)
        //        return BadRequest("Linked user not found");

        //    // 🔹 USER TABLE
        //    trainer.User.Uname = dto.Uname;
        //    trainer.User.Email = dto.Email;
        //    trainer.User.Contact = dto.Contact;
        //    trainer.User.Gender = dto.Gender;

        //    // 🔹 TRAINER TABLE
        //    trainer.Experience = dto.Experience;

        //    _context.SaveChanges();  // ✅ WILL UPDATE DB

        //    return Ok(new { message = "Profile updated successfully" });
        //}

        [HttpGet("members/{tid}")]
        public IActionResult GetAssignedMembers(int tid)
        {
            var members =
                from a in _context.MemberTrainerAssignments
                join m in _context.Members on a.Mid equals m.Mid
                join u in _context.Users on m.Uid equals u.Uid
                where a.Tid == tid
                select new AssignedMemberDto
                {
                    Mid = m.Mid,
                    Fname = u.Fname,
                    Lname = u.Lname,
                    Uname = u.Uname,
                    Email = u.Email,
                    Contact = u.Contact,
                    Height = m.Height,
                    Weight = m.Weight,
                    Status = m.Status

                };

            return Ok(members.ToList());
        }


    }

}

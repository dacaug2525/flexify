using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
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

        // READ
        [HttpGet("profile/{uid}")]
        public IActionResult GetProfile(int uid)
        {
            var trainerProfile =
                (from t in _context.Trainers
                 join u in _context.Users
                     on t.Uid equals u.Uid
                 where u.Uid == uid
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
                     Gender = u.Gender,
                   
                 }).FirstOrDefault();

                    if (trainerProfile == null)
                        return NotFound("Trainer not found");

                    return Ok(trainerProfile);
        }

        //// UPDATE
        //[HttpPut("profile")]
        //public IActionResult UpdateProfile(TrainerProfileDto dto)
        //{
        //    // 1️⃣ Find trainer
        //    var trainer = _context.Trainers.FirstOrDefault(t => t.Tid == dto.Tid);
        //    if (trainer == null)
        //        return NotFound("Trainer not found");

        //    // 2️⃣ Update trainer table (salary NOT touched)
        //    trainer.Experience = dto.Experience;

        //    // 3️⃣ Find linked user
        //    var user = _context.Users.FirstOrDefault(u => u.Uid == trainer.Uid);
        //    if (user == null)
        //        return NotFound("User not found");

        //    // 4️⃣ Update user table
        //    user.Fname = dto.Fname;
        //    user.Lname = dto.Lname;
        //    user.Contact = dto.Contact;
        //    user.Gender = dto.Gender;

        //    // 5️⃣ Save
        //    _context.SaveChanges();

        //    return Ok("Trainer profile updated successfully");
        //}

        [HttpGet("members/{tid}")]
        public IActionResult GetAssignedMembers(int tid)
        {
            var members = from a in _context.MemberTrainerAssignments
                          join m in _context.Members on a.Mid equals m.Mid
                          where a.Tid == tid
                          select new
                          {
                              m.Mid,
                              m.Weight,
                              m.Height,
                              m.Status
                          };

            return Ok(members.ToList());
        }

    }

}

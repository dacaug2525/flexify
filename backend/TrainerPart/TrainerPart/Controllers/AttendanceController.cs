using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;

namespace TrainerPart.Controllers
{
    [ApiController]
    [Route("api/attendance")]
    public class AttendanceController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public AttendanceController(TrainerDbContext context)
        {
            _context = context;
        }

        [HttpGet("trainer/{trainerId}")]
        public IActionResult GetAttendanceByTrainer(int trainerId)
        {
            var attendance =
                from a in _context.MemberAttendences
                join m in _context.Members on a.Mid equals m.Mid
                join u in _context.Users on m.Uid equals u.Uid
                join mt in _context.MemberTrainerAssignments on m.Mid equals mt.Mid
                where mt.Tid == trainerId
                select new MemberAttendanceDto
                {
                    AttendanceId = a.AttendenceId,
                    Mid = m.Mid,
                    MemberName = u.Fname + " " + u.Lname,
                    Email = u.Email,
                    Date = a.Date,
                    Status = a.Status.ToString()
                };

            return Ok(attendance.ToList());
        }

        [HttpGet("member/{mid}")]
        public IActionResult GetAttendanceByMember(int mid)
        {
            var attendance = from a in _context.MemberAttendences
                             join m in _context.Members on a.Mid equals m.Mid
                             join u in _context.Users on m.Uid equals u.Uid
                             where m.Mid == mid
                             select new MemberAttendanceDto
                             {
                                 AttendanceId = a.AttendenceId,
                                 Mid = m.Mid,
                                 MemberName = u.Fname + " " + u.Lname,
                                 Date = a.Date,
                                 Status = a.Status.ToString()
                             };

            return Ok(attendance.ToList());
        }

        [HttpPost("mark")]
        public IActionResult MarkAttendance(MarkAttendanceDto dto)
        {
            var existing = _context.MemberAttendences
                .FirstOrDefault(a => a.Mid == dto.Mid && a.Date.Date == dto.Date.Date);

            if (existing != null)
            {
                existing.Status = dto.Status;
            }
            else
            {
                var attendance = new MemberAttendence
                {
                    Mid = dto.Mid,
                    Date = dto.Date,
                    Status = dto.Status
                };
                _context.MemberAttendences.Add(attendance);
            }

            _context.SaveChanges();
            return Ok();
        }


    }
}

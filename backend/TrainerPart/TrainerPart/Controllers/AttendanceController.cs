using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;


namespace TrainerPart.Controllers
{
    [EnableCors]
    [ApiController]
    [Route("api/attendance")]
    public class AttendanceController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public AttendanceController(TrainerDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetAttendance()
        {
            var attendance = from a in _context.MemberAttendences
                             join m in _context.Members on a.Mid equals m.Mid
                             join u in _context.Users on m.Uid equals u.Uid
                             select new MemberAttendanceDto
                             {
                                 AttendanceId = a.AttendenceId,
                                 Mid = m.Mid,
                                 MemberName = u.Fname + " " + u.Lname,
                                 Date = a.Date,
                                 Status = a.Status
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
                                 Status = a.Status
                             };

            return Ok(attendance.ToList());
        }
    }
}

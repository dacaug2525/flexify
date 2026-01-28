using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;

namespace TrainerPart.Controllers
{
    [ApiController]
    [Route("api/progress")]
    public class ProgressController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public ProgressController(TrainerDbContext context)
        {
            _context = context;
        }


        // GET progress by member id
        [HttpGet("{mid}")]
        public IActionResult GetProgress(int memberId)
        {
            var progress = _context.MemberProgresses
                .Where(p => p.Mid == memberId)
                .Select(p => new ProgressDto
                {
                    ProgressId = p.ProgressId,
                    Mid = p.Mid,
                    Weight = p.Weight,
                    Bmi = p.Bmi,
                    RecordedDate = p.RecordedDate,
                    Remark = p.Remark
                })
                .ToList();

            if (progress.Count == 0)
                return NotFound("No progress found for this member");

            return Ok(progress);
        }


        [HttpPost("addProgress")]
        public IActionResult AddProgress(ProgressDto dto)
        {
            // get member
            var member = _context.Members.FirstOrDefault(m => m.Mid == dto.Mid);
            if (member == null)
                return BadRequest("Invalid Member Id");

            // height should be in meters
            if (member.Height <= 0)
                return BadRequest("Invalid height data for member");

            // BMI calculation
            double heightInMeters = member.Height / 100.0; // if height in cm

            if (heightInMeters <= 0)
            {
                return BadRequest("Invalid height value");
            }

            double bmi = dto.Weight / (heightInMeters * heightInMeters);
            var progress = new MemberProgress
            {
                Mid = dto.Mid,
                Weight = dto.Weight,
                Bmi = Math.Round(bmi, 2),   // round to 2 decimals
                RecordedDate = dto.RecordedDate == default
                                ? DateTime.Now
                                : dto.RecordedDate,
                Remark = dto.Remark
            };

            _context.MemberProgresses.Add(progress);
            _context.SaveChanges();

            return Ok("Progress added successfully");
        }


        [HttpPut("{progressId}")]
        public IActionResult UpdateProgress(int progressId, ProgressDto dto)
        {
            if (progressId != dto.ProgressId)
                return BadRequest("Progress ID mismatch");

            var progress = _context.MemberProgresses.Find(progressId);

            if (progress == null)
                return NotFound("Progress record not found");

            progress.Weight = dto.Weight;
            progress.Bmi = dto.Bmi;
            progress.Remark = dto.Remark;

            // optional update
            progress.RecordedDate = dto.RecordedDate == default
                                    ? progress.RecordedDate
                                    : dto.RecordedDate;

            _context.SaveChanges();
            return Ok("Progress updated successfully");
        }



    }

}

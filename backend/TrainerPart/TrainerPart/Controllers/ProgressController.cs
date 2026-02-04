using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;

[ApiController]
[Route("api/progress")]
public class ProgressController : ControllerBase
{
    private readonly TrainerDbContext _context;

    public ProgressController(TrainerDbContext context)
    {
        _context = context;
    }


    [HttpGet("trainer/{tid}/members")]
    public IActionResult GetTrainerMembers(int tid)
    {
        var members =
            from a in _context.MemberTrainerAssignments
            join m in _context.Members on a.Mid equals m.Mid
            join u in _context.Users on m.Uid equals u.Uid
            where a.Tid == tid
            select new
            {
                MemberId = m.Mid,
                MemberName = u.Fname + " " + u.Lname
            };

        return Ok(members.ToList());
    }


    [HttpGet("trainer/{tid}")]
    public IActionResult GetTrainerProgress(int tid)
    {
        var list =
            from p in _context.MemberProgresses
            join m in _context.Members on p.Mid equals m.Mid
            join u in _context.Users on m.Uid equals u.Uid
            join a in _context.MemberTrainerAssignments on m.Mid equals a.Mid
            where a.Tid == tid
            orderby p.RecordedDate descending
            select new ProgressDto
            {
                ProgressId = p.ProgressId,
                Mid = p.Mid,
                MemberName = u.Fname + " " + u.Lname,
                Weight = p.Weight,
                Bmi = p.Bmi,
                Remark = p.Remark
            };

        return Ok(list.ToList());
    }


    [HttpGet("trainer/{tid}/member/{mid}")]
    public IActionResult GetMemberProgress(int tid, int mid)
    {
        bool isAssigned = _context.MemberTrainerAssignments
            .Any(a => a.Tid == tid && a.Mid == mid);

        if (!isAssigned)
            return Unauthorized("Member not assigned to this trainer");

        var progressList =
            from p in _context.MemberProgresses
            join m in _context.Members on p.Mid equals m.Mid
            join u in _context.Users on m.Uid equals u.Uid
            where p.Mid == mid
            orderby p.RecordedDate descending
            select new ProgressDto
            {
                ProgressId = p.ProgressId,
                Mid = p.Mid,
                MemberName = u.Fname + " " + u.Lname,
                Weight = p.Weight,
                Bmi = p.Bmi,
                RecordedDate = p.RecordedDate,
                Remark = p.Remark
            };

        return Ok(progressList.ToList());
    }


    [HttpPost("trainer/{tid}")]
    public IActionResult AddProgress(int tid,ProgressCreateUpdateDto dto)
    {
        if (dto.Weight <= 0)
            return BadRequest("Invalid weight");

        bool isAssigned = _context.MemberTrainerAssignments
            .Any(a => a.Tid == tid && a.Mid == dto.Mid);

        if (!isAssigned)
            return Unauthorized("Member not assigned");

        var member = _context.Members.FirstOrDefault(m => m.Mid == dto.Mid);
        if (member == null)
            return BadRequest("Member not found");

        double heightMeters = member.Height / 100.0;
        double bmi = Math.Round(dto.Weight / (heightMeters * heightMeters), 2);

        var progress = new MemberProgress
        {
            Mid = dto.Mid,
            Weight = dto.Weight,
            Bmi = bmi,
            RecordedDate = DateTime.Now,
            Remark = dto.Remark
        };

        _context.MemberProgresses.Add(progress);
        _context.SaveChanges();

        return Ok("Progress added successfully");
    }

    [HttpPut("trainer/{tid}/{progressId}")]
    public IActionResult UpdateProgress(
    int tid,
    int progressId,
    ProgressCreateUpdateDto dto)
    {
        if (dto.Weight <= 0)
            return BadRequest("Invalid weight");

        var progress = _context.MemberProgresses.Find(progressId);
        if (progress == null)
            return NotFound("Progress not found");

        bool isAssigned = _context.MemberTrainerAssignments
            .Any(a => a.Tid == tid && a.Mid == progress.Mid);

        if (!isAssigned)
            return Unauthorized("Not allowed");

        var member = _context.Members.First(m => m.Mid == progress.Mid);
        double heightMeters = member.Height / 100.0;

        progress.Weight = dto.Weight;
        progress.Bmi = Math.Round(dto.Weight / (heightMeters * heightMeters), 2);
        progress.Remark = dto.Remark;
        progress.RecordedDate = DateTime.Now;

        _context.SaveChanges();

        return Ok("Progress updated successfully");
    }


    
}

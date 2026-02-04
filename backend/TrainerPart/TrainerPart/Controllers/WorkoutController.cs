using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;

[ApiController]
[Route("api/trainer/workouts")]
public class WorkoutScheduleController : ControllerBase
{
    private readonly TrainerDbContext _context;

    public WorkoutScheduleController(TrainerDbContext context)
    {
        _context = context;
    }

    // ✅ GET workouts for trainer
    [HttpGet("{trainerId}")]
    public IActionResult GetTrainerWorkouts(int trainerId)
    {
        var workouts =
            from w in _context.WorkoutSchedules
            join m in _context.Members on w.MemberId equals m.Mid
            join u in _context.Users on m.Uid equals u.Uid
            where w.TrainerId == trainerId
            select new
            {
                WorkoutId = w.WorkoutId,
                MemberId = m.Mid,
                MemberName = u.Fname + " " + u.Lname,
                WorkoutDesc = w.WorkoutDesc,
                Days = w.Days
            };

        var result = workouts.ToList();

        return Ok(result);
    }
    [HttpGet("trainer/{trainerId}/members")]
    public IActionResult GetMembersUnderTrainer(int trainerId)
    {
        var members =
            from a in _context.MemberTrainerAssignments
            join m in _context.Members on a.Mid equals m.Mid
            join u in _context.Users on m.Uid equals u.Uid
            where a.Tid == trainerId
            select new
            {
                MemberId = m.Mid,
                MemberName = u.Fname + " " + u.Lname
            };

        return Ok(members.Distinct().ToList());
    }

    [HttpPost]
    public IActionResult AddWorkout(AddUpdateWorkoutDto dto)
    {
        var workout = new WorkoutSchedule
        {
            TrainerId = dto.TrainerId,
            MemberId = dto.MemberId,
            WorkoutDesc = dto.WorkoutDesc,
            Days = dto.Days ?? 0
        };

        _context.WorkoutSchedules.Add(workout);
        _context.SaveChanges();

        return Ok("Workout Added");
    }

    [HttpPut("{workoutId}")]
    public IActionResult UpdateWorkout(int workoutId, AddUpdateWorkoutDto dto)
    {
        var workout = _context.WorkoutSchedules
            .FirstOrDefault(w => w.WorkoutId == workoutId);

        if (workout == null)
            return NotFound("Workout not found");

        workout.WorkoutDesc = dto.WorkoutDesc;
        workout.Days = dto.Days??0;

        _context.SaveChanges();

        return Ok("Workout Updated");
    }

}

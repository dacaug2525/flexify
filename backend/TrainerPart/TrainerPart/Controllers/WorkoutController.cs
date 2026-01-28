using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TrainerPart.DTO_s;
using TrainerPart.Models;

namespace TrainerPart.Controllers
{
    [EnableCors]
    [ApiController]
    [Route("api/workout")]
    public class WorkoutController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public WorkoutController(TrainerDbContext context)
        {
            _context = context;
        }

        [HttpPost("workoutAdd")]
        public IActionResult AddWorkout(WorkoutDto dto)
        {
            // 1️⃣ Validate Trainer exists
            var trainerExists = _context.Trainers.Any(t => t.Tid == dto.TrainerId);
            if (!trainerExists)
                return BadRequest("Invalid TrainerId");

            // 2️⃣ Validate Member exists
            var memberExists = _context.Members.Any(m => m.Mid == dto.MemberId);
            if (!memberExists)
                return BadRequest("Invalid MemberId");

            // 3️⃣ Create entity
            var workout = new WorkoutSchedule
            {
                TrainerId = dto.TrainerId,     // FK column name as per DB
                MemberId = dto.MemberId,
                WorkoutDesc = dto.WorkoutDesc,
                Days = dto.Days
            };

            _context.WorkoutSchedules.Add(workout);
            _context.SaveChanges();

            return Ok("Workout added successfully");
        }

        [HttpGet("member/{memberId}")]
        public IActionResult GetWorkout(int memberId)
        {
            var workouts = _context.WorkoutSchedules
                .Where(w => w.MemberId == memberId)   // FK column
                .Select(w => new WorkoutDto
                {
                    WorkoutId = w.WorkoutId,
                    WorkoutDesc = w.WorkoutDesc,
                    Days = w.Days
                })
                .ToList();

            if (workouts.Count == 0)
                return NotFound("No workouts assigned to this member");

            return Ok(workouts);
        }

        [HttpPut("{workoutId}")]
        public IActionResult UpdateWorkout(int workoutId, WorkoutDto dto)
        {
            if (workoutId != dto.WorkoutId)
                return BadRequest("Workout ID mismatch");

            var workout = _context.WorkoutSchedules.Find(workoutId);

            if (workout == null)
                return NotFound("Workout not found");

            workout.WorkoutDesc = dto.WorkoutDesc;
            workout.Days = dto.Days;

            _context.SaveChanges();
            return Ok("Workout updated successfully");
        }

        [HttpDelete("{workoutId}")]
        public IActionResult DeleteWorkout(int workoutId)
        {
            var workout = _context.WorkoutSchedules.Find(workoutId);

            if (workout == null)
                return NotFound("Workout not found");

            _context.WorkoutSchedules.Remove(workout);
            _context.SaveChanges();

            return Ok("Workout deleted successfully");
        }
    }

}

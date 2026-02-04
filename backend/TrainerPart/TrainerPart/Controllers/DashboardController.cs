using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TrainerPart.DTO_s;
using TrainerPart.Models;

namespace TrainerPart.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DashboardController : ControllerBase
    {
        private readonly TrainerDbContext _context;

        public DashboardController(TrainerDbContext context)
        {
            _context = context;
        }

        [HttpGet("dashboard/{trainerId}")]
        public IActionResult GetTrainerDashboard(int trainerId)
        {
            int totalMembers = _context.MemberTrainerAssignments
                .Where(a => a.Tid == trainerId)
                .Select(a => a.Mid)
                .Distinct()
                .Count();

            return Ok(new
            {
                totalMembers = totalMembers
            });
        }


    }
}

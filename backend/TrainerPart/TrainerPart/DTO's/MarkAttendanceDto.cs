using TrainerPart.Models;

namespace TrainerPart.DTO_s
{
    public class MarkAttendanceDto
    {
        public int Mid { get; set; }
        public DateTime Date { get; set; }
        public AttendanceStatus Status { get; set; }
    }
}

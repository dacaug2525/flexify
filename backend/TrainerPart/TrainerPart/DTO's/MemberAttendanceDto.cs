namespace TrainerPart.DTO_s
{
    public class MemberAttendanceDto
    {
        public int AttendanceId { get; set; }
    public int Mid { get; set; }

    public string MemberName { get; set; } // fname + lname
    public DateTime Date { get; set; }
    public string Status { get; set; }
    }
}

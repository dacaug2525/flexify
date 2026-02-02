namespace TrainerPart.DTO_s
{
  
        public class ProgressDto
        {
        public int ProgressId { get; set; }
        public int Mid { get; set; }
        public string MemberName { get; set; } = null!;
        public double Weight { get; set; }
        public double Bmi { get; set; }
        public string? Remark { get; set; }
        public DateTime RecordedDate { get; set; }
    }


}

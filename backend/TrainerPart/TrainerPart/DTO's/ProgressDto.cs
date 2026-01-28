namespace TrainerPart.DTO_s
{
    public class ProgressDto
    {
        public int ProgressId { get; set; }
        public int Mid { get; set; }
        public double Weight { get; set; }
        public DateTime RecordedDate { get; set; }
        public string? Remark { get; set; }

        // OUTPUT only
        public double Bmi { get; set; }
    }

}

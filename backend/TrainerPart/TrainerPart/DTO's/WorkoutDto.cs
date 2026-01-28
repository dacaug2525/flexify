namespace TrainerPart.DTO_s
{
    public class WorkoutDto
    {
        public int WorkoutId { get; set; }
        public int TrainerId { get; set; }
        public int MemberId { get; set; }
        public string WorkoutDesc { get; set; }
        public int Days { get; set; }
    }
}

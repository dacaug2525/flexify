namespace TrainerPart.DTO_s
{
    public class AddUpdateWorkoutDto
    {
        public int TrainerId { get; set; }
        public int MemberId { get; set; }
        public string WorkoutDesc { get; set; }
        public int? Days { get; set; }
    }
}

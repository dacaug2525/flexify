using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace TrainerPart.Models;

public partial class WorkoutSchedule
{
    [Key]
    public int WorkoutId { get; set; }

    public int TrainerId { get; set; }

    public int MemberId { get; set; }

    public string WorkoutDesc { get; set; }

    public int Days { get; set; }

    public virtual Member Member { get; set; } = null!;

    public virtual Trainer Trainer { get; set; } = null!;
}

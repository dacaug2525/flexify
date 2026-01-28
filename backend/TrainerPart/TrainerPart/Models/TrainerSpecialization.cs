using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class TrainerSpecialization
{
    public int TrainingId { get; set; }

    public int Tid { get; set; }

    public int TrId { get; set; }

    public string? Description { get; set; }

    public virtual Trainer TidNavigation { get; set; } = null!;

    public virtual TrainingTable Tr { get; set; } = null!;
}

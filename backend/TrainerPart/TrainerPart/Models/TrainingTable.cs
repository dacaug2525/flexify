using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class TrainingTable
{
    public int TrId { get; set; }

    public string TrName { get; set; } = null!;

    public string Desc { get; set; } = null!;

    public virtual ICollection<TrainerSpecialization> TrainerSpecializations { get; set; } = new List<TrainerSpecialization>();
}

using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class Trainer
{
    public int Tid { get; set; }

    public int Experience { get; set; }

    public decimal Salary { get; set; }

    public int Uid { get; set; }

    public virtual ICollection<MemberTrainerAssignment> MemberTrainerAssignments { get; set; } = new List<MemberTrainerAssignment>();

    public virtual ICollection<TrainerSpecialization> TrainerSpecializations { get; set; } = new List<TrainerSpecialization>();

    public virtual User UidNavigation { get; set; } = null!;

    public virtual ICollection<WorkoutSchedule> WorkoutSchedules { get; set; } = new List<WorkoutSchedule>();
}

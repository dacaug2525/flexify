using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class Member
{
    public int Mid { get; set; }

    public DateTime Dob { get; set; }

    public int Height { get; set; }

    public int Weight { get; set; }

    public string Address { get; set; } = null!;

    public DateTime JoinDate { get; set; }

    public string Status { get; set; } = null!;

    public int Uid { get; set; }

    public virtual ICollection<MemberAttendence> MemberAttendences { get; set; } = new List<MemberAttendence>();

    public virtual ICollection<MemberProgress> MemberProgresses { get; set; } = new List<MemberProgress>();

    public virtual ICollection<MemberTrainerAssignment> MemberTrainerAssignments { get; set; } = new List<MemberTrainerAssignment>();

    public virtual User UidNavigation { get; set; } = null!;

    public virtual ICollection<WorkoutSchedule> WorkoutSchedules { get; set; } = new List<WorkoutSchedule>();
}

using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class MemberAttendence
{
    public int AttendenceId { get; set; }

    public int Mid { get; set; }

    public DateTime Date { get; set; }

    public AttendanceStatus Status { get; set; }

    public virtual Member MidNavigation { get; set; } = null!;
}

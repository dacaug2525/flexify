using System;
using System.Collections.Generic;

namespace TrainerPart.Models;

public partial class MemberProgress
{
    public int ProgressId { get; set; }

    public int Mid { get; set; }

    public double Weight { get; set; }

    public double Bmi { get; set; }

    public DateTime RecordedDate { get; set; }

    public string? Remark { get; set; }

    public virtual Member MidNavigation { get; set; } = null!;
}

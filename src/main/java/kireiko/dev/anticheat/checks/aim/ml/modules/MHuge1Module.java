package kireiko.dev.anticheat.checks.aim.ml.modules;

import kireiko.dev.millennium.math.Simplification;
import kireiko.dev.millennium.ml.data.ResultML;
import kireiko.dev.millennium.ml.data.module.FlagType;
import kireiko.dev.millennium.ml.data.module.ModuleML;
import kireiko.dev.millennium.ml.data.module.ModuleResultML;

public class MHuge1Module implements ModuleML {
    @Override
    public String getName() {
        return "m_huge1";
    }

    @Override
    public ModuleResultML getResult(ResultML resultML) {
        ResultML.CheckResultML checkResult = resultML.statisticsResult;
        double ab1 = checkResult.UNUSUAL;
        double ab2 = checkResult.STRANGE;
        double ab3 = checkResult.SUSPECTED;
        double ab4 = checkResult.SUSPICIOUSLY;
        FlagType type = FlagType.NORMAL;
        if (ab1 > 1.0) {
            double percent = Math.min(((ab1 - 1.0)) * 100, 100.0);
            if (percent > 80) {
                type = FlagType.SUSPECTED;
            } else if (percent > 70) {
                type = FlagType.STRANGE;
            }
        } else if (ab1 < 0.75 && ab4 < 0.02 && ab4 != 0.00 && ab3 == 0) {
            type = FlagType.STRANGE;
        } else if (ab1 < 0.25 && ab2 < 0.12 && ab3 < 0.03 && ab4 < 0.015) {
            type = FlagType.UNUSUAL;
        }
        return new ModuleResultML(30, type, checkResult.toString());
    }
}

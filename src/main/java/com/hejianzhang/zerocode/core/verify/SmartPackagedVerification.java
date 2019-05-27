package com.hejianzhang.zerocode.core.verify;

import com.hejianzhang.zerocode.core.domain.TargetEnv;
import com.hejianzhang.zerocode.core.domain.TestPackageRoot;
import com.hejianzhang.zerocode.core.runner.ZeroCodePackageRunner;
import org.junit.runner.RunWith;

@TargetEnv("dev_test.properties")
@TestPackageRoot("01_verification_test_cases")
@RunWith(ZeroCodePackageRunner.class)
public class SmartPackagedVerification {

}

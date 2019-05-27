import org.jsmart.simulator.annotations.TargetEnv;
import com.hejianzhang.zerocode.core.domain.JsonTestCase;
import com.hejianzhang.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("vms.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class VmsTest {

    @Test
    @JsonTestCase("helloworld/vms.json")
    public void testGet() throws Exception {
    }

}

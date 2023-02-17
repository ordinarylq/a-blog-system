package cc.lq.blog.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author qili
 * @create 2023-02-17-18:26
 */

@SpringBootTest
public class MpCodeGenTest {

    /**
     * 项目根路径
     */
    @Value("${user.dir}")
    private String path;

    @Test
    void generateCode() {
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(
                "jdbc:mysql://localhost:3306/a_blog_system?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root", "123456")
                .dbQuery(new MySqlQuery())
                .schema("a_blog_system")
                .typeConvert(new MySqlTypeConvert());

        FastAutoGenerator
                // 1. 数据源配置
                .create(dataSourceConfigBuilder)
                // 2. 全局配置
                .globalConfig(builder ->
                        builder.outputDir(path)
                                .author("Qi Li")
                                .dateType(DateType.TIME_PACK)
                                .commentDate("yyyy-MM-dd")
                                .disableOpenDir())
                // 3. 包配置
                .packageConfig(builder ->
                        builder.parent("cc.lq.blog.system")
                                .xml("mapper.mapper"))
                // 4. 策略配置
                .strategyConfig(builder ->
                        builder.addTablePrefix("blog_"))
                // 5. entity配置
                .strategyConfig(builder ->
                        builder.entityBuilder()
                                .superClass("cc.lq.blog.system.BaseEntity")
                                .naming(NamingStrategy.underline_to_camel)
                                .disableSerialVersionUID()
                                .enableRemoveIsPrefix()
                                .logicDeleteColumnName("is_deleted")
                                .addSuperEntityColumns("id", "create_time", "update_time")
                                .idType(IdType.AUTO)
                                .formatFileName("%sDO")
                                .enableFileOverride())
                // 6. mapper配置
                .strategyConfig(builder ->
                        builder.mapperBuilder()
                                .superClass(BaseMapper.class)
                                .enableBaseResultMap()
                                .enableBaseColumnList()
                                .formatMapperFileName("%sMapper")
                                .formatXmlFileName("%sMapper")
                                .enableFileOverride())
                // 7. service配置
                .strategyConfig(builder ->
                        builder.serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                                .enableFileOverride())
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

}

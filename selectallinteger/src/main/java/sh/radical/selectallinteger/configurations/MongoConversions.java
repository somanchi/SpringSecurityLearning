package sh.radical.selectallinteger.configurations;


import com.mongodb.lang.NonNull;
import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

@Configuration
public class MongoConversions {

    @Bean
    public MongoCustomConversions  mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(

                new Converter<BigDecimal, Decimal128>() {

                    @Override
                    public Decimal128 convert(@NonNull BigDecimal source) {
                        return new Decimal128(source);
                    }
                },

                new Converter<Decimal128, BigDecimal>() {

                    @Override
                    public BigDecimal convert(@NonNull Decimal128 source) {
                        return source.bigDecimalValue();
                    }

                },
                new Converter<Long, Instant>() {

                    @Override
                    public Instant convert(Long timeEpochMilli) {
                        return Instant.ofEpochMilli(timeEpochMilli);
                    }
                },

                new Converter<Instant, Long>() {

                    @Override
                    public Long convert(Instant instant) {
                        return instant.toEpochMilli();
                    }
                }

        ));

    }
}
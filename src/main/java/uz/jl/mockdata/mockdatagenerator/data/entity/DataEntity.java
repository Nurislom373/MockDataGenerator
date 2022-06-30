package uz.jl.mockdata.mockdatagenerator.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "code", unique = true, nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID code;

    @Column(name = "table_name", nullable = false, length = 100)
    private String tableName;

    @Column(name = "model", nullable = false, columnDefinition = "json")
    private String fileType;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "download_type", nullable = false, length = 100)
    private String downloadType;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}

package uz.jl.mockdata.mockdatagenerator.data.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "table_name", nullable = false, length = 100)
    private String tableName;

    @Column(name = "model", nullable = false, columnDefinition = "json")
    private String model;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "download_type", nullable = false, length = 100)
    private String downloadType;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}

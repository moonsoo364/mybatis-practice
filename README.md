## 개요

Mybatis 와 JPA 를 비교해서 두 라이브러리의 차이점을 비교하기 위해 코드를 작성했다.

## Mybatis 와 JPA 비교

### 1. 애플리케이션 연결

Mybatis의 경우 데이터베이스 URL 및 유저가 일치하지 않아도 애플리케이션이 실행된다.
JPA의 경우에 애플리케이션 실행 시 Entity 클래스와 테이블을 매핑시키는 작업을 실행하므로 DB URL이 다르면 애플리케이션이 실행되지 않는다. Mybatis에서 애플리케이션 실행 중 데이터베이스에 연결하고 싶으면 아래와 같은 설정을 추가하면 된다.

```yaml
spring:
  sql:
    init:
      data-locations: classpath:db/test.sql
      mode: always

```

```sql
/*test.sql*/
SELECT 1;

```

### 2. 객체 매핑

Mybatis에서 SELECT 절의 결과를 객체로 매핑할 경우 SQL이 담기는 XML 파일에 resultMap 태그를 추가 해줘야 한다. JPA의 경우에는 기본적으로 Entity 객체를 반환하고 DTO 객체로 반환하고 싶을 때는 Projection을 이용하여 JPQL 문법으로 작성해야 한다. 이 경우 `@Query` 어노테이션에 생성자를 포함한 긴 문자열을 작성 해야 한다.

```xml
<resultMap id = "StockResultMap" type="org.example.stock.domain.Stock">
       <id column="STOCK_ID" property="stockId"/>
       <result column="STOCK_NAME" property="stockName"/>
       <result column="STOCK_EXCHANGE" property="stockExchange"/>
       <result column="CURRENT_PRICE" property="currentPrice"/>
       <result column="CREATED_DATE" property="createdDate"/>
   </resultMap>   
```

```java
@Query("SELECT org.example.stock.domain.StockDto(stockId,stockName...) FROM Stock s")
List<User> findAllUsers();
```

### 3. 동적 쿼리

Mybatis 의 경우 XML에서 `<if>`태그를 이용하여 동적 쿼리를 작성할 수 있다.  데이터베이스의 모든 컬럼을 동적쿼리로 처리해야 하는 경우 SQL 문장이 길어지는 단점이 있다. JPA 경우에는 Entity 클래스의 필드에 `@DynamicUpdate`  어노테이션을 이용하여 간단하게 처리할 수 있다.  `@DynamicUpdate` 어노테이션의 경우에는 변경된 필드를 대상으로  Update문을 실행하기 때문에 완전히 동일하진 않다. 

```xml
<update id="update" parameterType="org.example.stock.domain.Stock">
        UPDATE
            STOCK
        <set>
            <if test="stockId != null">
                STOCK_ID = #{stockId},
            </if>
        </set>
        WHERE STOCK_ID = #{stockId}
    </update>
```

```java
@Entity
public class Stock{

	@DynamicUpdate
	@Column(name ="STOCK_ID")
	private String stockId;
}
```

### 4. SQL 의존성

Mybatis는 특정 DB에 호환되는 함수와 SQL 문법을 사용해야 한다. 만약 여러 데이터베이스와 호환되는 애플리케이션을 개발할 예정이라면 Mybatis 보단 JPA가 적합하다. 또한 정규화, 테이블 리팩터링 등 데이터베이스 컬럼 및 테이블이 변경되야하는 경우 Mybatis는 해당 테이블을 사용하는 SQL 모두 바꿔야 하는 반면 JPA는 Entity 클래스의 `@Column` , `@Table`  어노테이션의 속성을 변경하여 쉽게 호환성을 가질 수 있다.

### 5. 어느것을 선택해야 할까?

Mybatis는 NativeQuery 기반으로 최적화된 쿼리를 작성할 수 있다. 또한 Inline View, 함수 등 데이터베이스에서 사용할 수 있는 대부분의 기능을 사용할 수 있어  애플케이션에 맞는 SQL을 선택해서 사용할 수 있는 장점이 있다. 하지만 데이터베이스 구조가 자주 바뀔경우 기존에 사용하던 SQL을 모두 바꿔야하는 작업공수가 클 수 있다.

JPA의 경우 데이터베이스와 상관없이 하나의 메서드로 SQL을 관리할 수 있는 장점이 있다. 또한 어노테이션과 조합하여 간단하게 SQL을 만들 수 있어 편리하다. 하지만 특정 데이터베이스를 함수를 쓰거나 DTO 객체에 SQL 결과를 매핑해야 하는 경우 긴 문자열의 쿼리문을 작성해야 하기 때문에 가독성이 떨어진다. 또 Persitence Contenxt와 같은 JPA만의 Entity 관리 방법에 대한 이해가 필요하다.

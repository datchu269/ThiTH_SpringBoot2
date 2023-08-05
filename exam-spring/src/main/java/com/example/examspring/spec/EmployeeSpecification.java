package com.example.examspring.spec;

import com.example.examspring.dto.EmployeeDto;
import com.example.examspring.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSpecification {
    public Specification<Employee> filter(final EmployeeDto criteria){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(criteria.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+criteria.getName()+"%"));
            }

//            if (StringUtils.isNotEmpty(criteria.getCode())) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%"+criteria.getCode()+"%"));
//            }

            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}

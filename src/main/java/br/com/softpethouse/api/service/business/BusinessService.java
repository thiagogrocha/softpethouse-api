package br.com.softpethouse.api.service.business;

import br.com.softpethouse.api.service.business.dto.BusinessDtoCreate;
import br.com.softpethouse.api.service.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequestScoped
public class BusinessService implements PanacheRepository<BusinessEntity> {

    private String businessNotFound = "Negócio não encontrado! Id: ";

    public List<BusinessDtoOut> business() {
        return findAll(Sort.by("username", Sort.Direction.Ascending)).list()
                .stream().map(BusinessDtoOut::toDto).collect(Collectors.toList());
    }

    public BusinessDtoOut business(long id) throws EntityNotFoundException {
        BusinessEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(businessNotFound + id));

        return BusinessDtoOut.toDto(entity);
    }

    public long create(BusinessDtoCreate dto) {
        BusinessEntity entity = BusinessDtoCreate.toEntity(dto);

        persist(entity);

        return entity.getId();
    }

    public boolean update(long id, BusinessDtoCreate dto) throws EntityNotFoundException {
        BusinessEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(businessNotFound + id));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return true;
    }

    public boolean disable(long id) throws EntityNotFoundException {
        BusinessEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(businessNotFound + id));

        entity.setActive("N");

        return true;
    }

}

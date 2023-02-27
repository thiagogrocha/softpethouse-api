package br.com.softpethouse.api.infra.repository.account;

import br.com.softpethouse.api.service.account.dto.TypeAccountDtoCreate;
import br.com.softpethouse.api.service.account.dto.TypeAccountDtoOut;
import br.com.softpethouse.api.domain.account.TypeAccountEntity;
import br.com.softpethouse.api.service.account.TypeAccountService;
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
public class TypeAccountRepository implements TypeAccountService, PanacheRepository<TypeAccountEntity> {

    private String typeAccountNotFound = "Tipo de Conta não encontrada! Id: ";

    @Override
    public List<TypeAccountDtoOut> typesAccount() {
        return findAll(Sort.by("name", Sort.Direction.Ascending)).list()
                .stream().map(TypeAccountDtoOut::toDto).collect(Collectors.toList());
    }

    @Override
    public TypeAccountDtoOut typesAccount(long id) throws EntityNotFoundException {
        TypeAccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(typeAccountNotFound + id));

        return TypeAccountDtoOut.toDto(entity);
    }

    @Override
    public long create(TypeAccountDtoCreate dto) {
        TypeAccountEntity entity = TypeAccountDtoCreate.toEntity(dto);

        persist(entity);

        return entity.getId();
    }

    @Override
    public boolean update(long id, TypeAccountDtoCreate dto) throws EntityNotFoundException {
        TypeAccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(typeAccountNotFound + id));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return true;
    }

    @Override
    public boolean disable(long id) throws EntityNotFoundException {
        TypeAccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(typeAccountNotFound + id));

        entity.setActive("N");

        return true;
    }

}

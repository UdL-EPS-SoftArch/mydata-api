package cat.udl.eps.entsoftarch.handler;

import cat.udl.eps.entsoftarch.domain.DataOwner;
import cat.udl.eps.entsoftarch.domain.Dataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Component
@Transactional
@RepositoryEventHandler(Dataset.class)
public class DatasetEventHandler {
    private final Logger logger = LoggerFactory.getLogger(DatasetEventHandler.class);

    @HandleBeforeCreate
    @PreAuthorize("hasRole('OWNER')")
    public void handleDatasetPreCreate(Dataset dataset) {
        logger.info("Before creating: {}", dataset.toString());

        dataset.setDateTime(ZonedDateTime.now());
        DataOwner principal = (DataOwner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dataset.setOwner(principal);
    }

    @HandleBeforeSave
    @PreAuthorize("#dataset.owner.username == principal.username")
    public void handleDatasetPreSave(Dataset dataset){
        dataset.setLastModified(ZonedDateTime.now());
        logger.info("Before updating: {}", dataset.toString());
    }

    @HandleBeforeDelete
    @PreAuthorize("#dataset.owner.username == principal.username")
    public void handleDatasetPreDelete(Dataset dataset){
        logger.info("Before deleting: {}", dataset.toString());
    }

    @HandleBeforeLinkSave
    public void handleDatasetPreLinkSave(Dataset dataset, Object o) {
        logger.info("Before linking: {} to {}", dataset.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleDatasetPostCreate(Dataset dataset){
        logger.info("After creating: {}", dataset.toString());
    }

    @HandleAfterSave
    public void handleDatasetPostSave(Dataset dataset){
        logger.info("After updating: {}", dataset.toString());
    }

    @HandleAfterDelete
    public void handleDatasetPostDelete(Dataset dataset){
        logger.info("After deleting: {}", dataset.toString());
    }

    @HandleAfterLinkSave
    public void handleDatasetPostLinkSave(Dataset dataset, Object o) {
        logger.info("After linking: {} to {}", dataset.toString(), o.toString());
    }
}

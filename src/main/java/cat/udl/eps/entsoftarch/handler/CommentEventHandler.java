package cat.udl.eps.entsoftarch.handler;

import cat.udl.eps.entsoftarch.domain.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
@Transactional
@RepositoryEventHandler(Comment.class)
public class CommentEventHandler {
    private final Logger logger = LoggerFactory.getLogger(CommentEventHandler.class);

    @HandleBeforeCreate
    @PreAuthorize("#comment.about.owner.username == principal.username")
    public void handleCommentPreCreate(Comment comment) {
        logger.info("Before creating: {}", comment.toString());

    }

    @HandleBeforeSave
    @PreAuthorize("#comment.about.owner.username == principal.username")
    public void handleCommentPreSave(Comment comment) {
        logger.info("Before updating: {}", comment.toString());
    }

    @HandleBeforeDelete
    @PreAuthorize("#comment.about.owner.username == principal.username")
    public void handleCommentPreDelete(Comment comment) {
        logger.info("Before deleting: {}", comment.toString());
    }

    @HandleBeforeLinkSave
    @PreAuthorize("#comment.about.owner.username == principal.username")
    public void handleCommentPreLinkSave(Comment comment, Object o) {
        logger.info("Before linking: {} to {}", comment.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleCommentPostCreate(Comment comment) {
        logger.info("After creating: {}", comment.toString());
    }

    @HandleAfterSave
    public void handleCommentPostSave(Comment comment) {
        logger.info("After updating: {}", comment.toString());
    }

    @HandleAfterDelete
    public void handleCommentPostDelete(Comment comment) {
        logger.info("After deleting: {}", comment.toString());
    }

    @HandleAfterLinkSave
    public void handleCommentPostLinkSave(Comment comment, Object o) {
        logger.info("After linking: {} to {}", comment.toString(), o.toString());
    }
}

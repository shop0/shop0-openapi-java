package com.xshop0.openapi.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLError;

import java.util.Arrays;
import java.util.List;

public class Shop0ApiException extends Exception {
    private List<GraphQLError> errors;
    public Shop0ApiException(List<GraphQLError> errors) {
        this.errors = errors;
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return Arrays.toString(errors.stream().map(graphQLError -> graphQLError.getMessage()).toArray());
    }
}

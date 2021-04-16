package com.github.shop0.openapi.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.github.shop0.openapi.graphql.api.QueryResolver;
import com.github.shop0.openapi.graphql.model.*;

import javax.validation.constraints.NotNull;

public class QueryResolverImpl implements QueryResolver {
    private Shop0Client client;
    public QueryResolverImpl(Shop0Client client) {
        this.client = client;
    }

    @Override
    public OrderTO order(@NotNull String id) throws Exception {
        OrderQueryRequest req = new OrderQueryRequest();
        req.setId(id);

        OrderResponseProjection proj = new OrderResponseProjection().all$(4);
        GraphQLRequest graphQLRequest = new GraphQLRequest(req, proj);
        OrderQueryResponse result = this.client.invoke(graphQLRequest, OrderQueryResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }

        return result.order();
    }

    @Override
    public @NotNull OrderConnectionTO orders(String after, String before, Integer first, Integer last, Boolean reverse) throws Exception {
        OrdersQueryRequest queryRequest = new OrdersQueryRequest();
        queryRequest.setAfter(after);
        queryRequest.setBefore(before);
        queryRequest.setFirst(first);
        queryRequest.setLast(last);
        queryRequest.setReverse(reverse);

        OrderConnectionResponseProjection responseProjection = new OrderConnectionResponseProjection().totalCount();
        GraphQLRequest graphQLRequest = new GraphQLRequest(queryRequest, responseProjection);

        OrdersQueryResponse result = client.invoke(graphQLRequest, OrdersQueryResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }
        return result.orders();
    }

    @Override
    public CustomerTO customer(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public ProductTO product(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public ProductVariantTO productVariant(@NotNull String id) throws Exception {
        return null;
    }

    @Override
    public @NotNull ShopTO shop() throws Exception {
        return null;
    }
}

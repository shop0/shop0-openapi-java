package io.github.shop0.openapi.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import io.github.shop0.openapi.graphql.api.QueryResolver;
import io.github.shop0.openapi.graphql.model.*;

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

    public OrderResponseProjection getOrderResponseProjection(){
        OrderResponseProjection proj = new OrderResponseProjection()
                .id()
//                .discountApplications(null)
//                .events(null)
                .shippingAddress(new MailingAddressResponseProjection().all$())
                .shippingLine(new ShippingLineResponseProjection()
                        .originalPriceSet(new MoneyBagResponseProjection().all$())
                        .phone())
//                .subtotalLineItemsQuantity()
//                .tags(null)
                .refunds(new RefundResponseProjection()
                        .id()
                        .refundLineItems(new RefundLineItemConnectionResponseProjection()
                                .edges(new RefundLineItemEdgeResponseProjection()
                                        .node(new RefundLineItemResponseProjection()
                                                .lineItem(new LineItemResponseProjection().id())
                                                .location(new LocationResponseProjection().address(new LocationAddressResponseProjection().all$()))
                                                .priceSet(new MoneyBagResponseProjection().all$())
                                                .quantity()))))
//                .totalWeight()
//                .refundable()
                .processedAt()
//                .metafields(null)
//                .metafield(null)
//                .privateMetafields(null)
//                .privateMetafield(null)
//                .fullyPaid()
                .lineItems(new LineItemConnectionResponseProjection()
                        .edges(new LineItemEdgeResponseProjection()
                                .node(new LineItemResponseProjection()
                                        .id())))
                .fulfillments(new FulfillmentResponseProjection()
                        .id())
//                .risks(null)
//                .totalCapturableSet(null)
//                .totalDiscountsSet(null)
//                .totalOutstandingSet(null)
                .totalPriceSet(new MoneyBagResponseProjection().all$())
//                .totalReceivedSet(null)
//                .totalRefundedSet(null)
//                .totalRefundedShippingSet(null)
//                .totalShippingPriceSet(null)
//                .totalTaxSet(null)
//                .totalTipReceivedSet(null)
//                .subtotalPriceSet(null)
//                .transactions(null)
                .unpaid()
//                .updatedAt()
//                .taxesIncluded()
//                .taxLines(null)
                ;
        return proj;
    }
}

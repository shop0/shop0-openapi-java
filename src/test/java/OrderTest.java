import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import io.github.shop0.openapi.graphql.api.QueryResolver;
import io.github.shop0.openapi.graphql.QueryResolverImpl;
import io.github.shop0.openapi.graphql.Shop0ApiException;
import io.github.shop0.openapi.graphql.Shop0Client;
import io.github.shop0.openapi.graphql.model.*;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Shop0Client shop0Client;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        shop0Client = new Shop0Client.Builder().
                host("http://127.0.0.1:10250/admin/v1/graphql").
                debug(true).
                accessToken("").
                build();
    }

    @Test
    public void testOrderResolver() throws Exception {
        QueryResolver queryResolver = new QueryResolverImpl(shop0Client);
        OrderTO order = queryResolver.order("111");
        System.out.println(order);
    }

    @Test
    public void testOrder() throws Exception {
        OrderQueryRequest req = new OrderQueryRequest();
        req.setId("1");

        OrderResponseProjection proj = new OrderResponseProjection()
                .id()
//                .discountApplications(null)
//                .events(null)
                .shippingAddress(new MailingAddressResponseProjection().all$())
                .shippingLine(new ShippingLineResponseProjection()
                        .originalPriceSet(new MoneyBagResponseProjection().all$())
                        .phone()
                        .requestedFulfillmentService(new FulfillmentServiceResponseProjection()
                                .location(new LocationResponseProjection()
                                        .inventoryLevel(new LocationInventoryLevelParametrizedInput().inventoryItemId("111"), new InventoryLevelResponseProjection().id())
                                        .fulfillmentService(new FulfillmentServiceResponseProjection().id())
                                ))
                )
//                .subtotalLineItemsQuantity()
//                .tags(null)
                .refunds(new RefundResponseProjection()
                        .id()
                        .refundLineItems(new RefundLineItemConnectionResponseProjection()
                                .edges(new RefundLineItemEdgeResponseProjection()
                                        .node(new RefundLineItemResponseProjection()
                                                .lineItem(new LineItemResponseProjection()
//                                                        .fulfillmentStatus()
                                                        .id()
                                                        .product(new ProductResponseProjection().id())
                                                        .quantity()
                                                        .sku())
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
//                                        .fulfillmentStatus()
                                        .id()
                                        .image(new ImageResponseProjection().all$())
                                        .product(new ProductResponseProjection().id())
                                        .quantity()
                                        .sku()
                                        .title()
                                        )))
                .fulfillments(new FulfillmentResponseProjection()
                        .id()
//                        .displayStatus()
                        .createdAt()
                        .updatedAt()
//                        .status()
                       )
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
                .updatedAt()
//                .taxesIncluded()
//                .taxLines(new TaxLineResponseProjection())
                ;
        GraphQLRequest graphQLRequest = new GraphQLRequest(req, proj);
        OrderQueryResponse result = shop0Client.invoke(graphQLRequest, OrderQueryResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }

        System.out.println(result.order());
    }

    @Test
    public void testOrders() throws Exception {
        OrdersQueryRequest req = new OrdersQueryRequest();
        req.setFirst(10);

        OrderConnectionResponseProjection proj = new OrderConnectionResponseProjection()
                .pageInfo(new PageInfoResponseProjection().all$(2))
                .edges(new OrderEdgeResponseProjection().cursor().node(
                        new OrderResponseProjection().id().totalWeight()
                ));
        GraphQLRequest graphQLRequest = new GraphQLRequest(req, proj);
        OrdersQueryResponse result = shop0Client.invoke(graphQLRequest, OrdersQueryResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }

        System.out.println(result.orders());
    }
}

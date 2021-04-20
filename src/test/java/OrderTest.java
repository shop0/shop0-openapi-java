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

        OrderResponseProjection proj = getOrderResponseProjection();
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
                .pageInfo(new PageInfoResponseProjection().all$())
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

    public OrderResponseProjection getOrderResponseProjection(){
        OrderResponseProjection proj = new OrderResponseProjection()
                .id()
//                .discountApplications(null)
//                .events(null)
//                .fulfillmentOrders()
                .lineItems(new LineItemConnectionResponseProjection()
                                .edges(new LineItemEdgeResponseProjection()
                                        .node(new LineItemResponseProjection()
//                                                        .fulfillmentStatus()
                                                        .id()
                                                        .image(new ImageResponseProjection().all$())
                                                        .product(new ProductResponseProjection()
                                                                .id()
                                                                .title()
                                                                .images(new ImageConnectionResponseProjection()
                                                                        .edges(new ImageEdgeResponseProjection()
                                                                                .node(new ImageResponseProjection().all$())))
                                                                .description()
                                                                .details()
                                                        )
                                                        .quantity()
                                                        .sku()
                                                        .title()
                                        ))
                )
//                .localizationExtensions()
//                .metafields(null)
//                .nonFulfillableLineItems()
//                .privateMetafields(null)
//                .shippingLines()
//                .alerts()
//                .billingAddress()
//                .billingAddressMatchesShippingAddress()
//                .canMarkAsPaid()
//                .canNotifyCustomer()
//                .cancelReason()
//                .cancelledAt()
//                .capturable()
//                .cartDiscountAmountSet()
//                .clientIp()
//                .closed()
//                .closedAt()
//                .confirmed()
//                .createdAt()
//                .currencyCode()
//                .currentCartDiscountAmountSet()
//                .currentSubtotalLineItemsQuantity()
//                .currentSubtotalPriceSet()
//                .currentTaxLines()
//                .currentTotalDiscountsSet()
//                .currentTotalDutiesSet()
//                .currentTotalPriceSet()
//                .currentTotalTaxSet()
//                .currentTotalWeight()
//                .customAttributes()
//                .customer()
//                .customerAcceptsMarketing()
//                .customerJourneySummary()
//                .customerLocale()
//                .discountCode()
//                .displayAddress()
//                .displayFinancialStatus()
//                .displayFulfillmentStatus()
//                .disputes()
//                .edited()
//                .email()
//                .fulfillable()
//                .fullyPaid()
//                .hasTimelineComment()
//                .merchantEditable()
//                .merchantEditableErrors()
//                .metafield()
//                .name()
//                .netPaymentSet()
//                .note()
//                .originalTotalDutiesSet()
//                .originalTotalPriceSet()
//                .paymentCollectionDetails()
//                .paymentGatewayNames()
//                .phone()
//                .physicalLocation()
//                .presentmentCurrencyCode()
//                .privateMetafield()
//                .processedAt()
//                .publication()
//                .refundDiscrepancySet()
//                .refundable()
                .refunds(new RefundResponseProjection()
                                .id()
                                .refundLineItems(new RefundLineItemConnectionResponseProjection()
                                        .edges(new RefundLineItemEdgeResponseProjection()
                                                .node(new RefundLineItemResponseProjection()
                                                        .lineItem(new LineItemResponseProjection()
//                                                        .fulfillmentStatus()
                                                                .id()
                                                                .product(new ProductResponseProjection()
                                                                        .id()
                                                                        .title()
                                                                        .images(new ImageConnectionResponseProjection()
                                                                                .edges(new ImageEdgeResponseProjection()
                                                                                        .node(new ImageResponseProjection().all$())))
                                                                        .description()
                                                                        .details()
                                                                )
                                                                .quantity()
                                                                .sku())
                                                        .location(new LocationResponseProjection()
                                                                .address(new LocationAddressResponseProjection().all$()))
                                                        .priceSet(new MoneyBagResponseProjection().all$())
                                                        .quantity())))
                )
//                .requiresShipping()
//                .restockable()
//                .riskLevel()
//                .risks()
                .shippingAddress(new MailingAddressResponseProjection().all$())
                .shippingLine(new ShippingLineResponseProjection()
                                .originalPriceSet(new MoneyBagResponseProjection().all$())
                                .phone()
/*                        .requestedFulfillmentService(new FulfillmentServiceResponseProjection()
                                .location(new LocationResponseProjection()
                                        .inventoryLevel(new LocationInventoryLevelParametrizedInput().inventoryItemId("111"), new InventoryLevelResponseProjection().id())
                                        .fulfillmentService(new FulfillmentServiceResponseProjection().id())
                                )
                        )*/
                )
//                .subtotalLineItemsQuantity()
//                .subtotalPriceSet()
//                .suggestedRefund()
//                .tags(null)
//                .taxLines(new TaxLineResponseProjection())
//                .taxesIncluded()
//                .test()
//                .totalWeight()
//                .refundable()
                .processedAt()
//                .metafield(null)
//                .privateMetafield(null)
//                .fullyPaid()
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
                ;
        return proj;
    }

}

package io.github.shop0.openapi.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import io.github.shop0.openapi.graphql.api.MutationResolver;
import io.github.shop0.openapi.graphql.api.QueryResolver;
import io.github.shop0.openapi.graphql.model.*;

import javax.validation.constraints.NotNull;

public class MutationResolverImpl implements MutationResolver {

    private final Shop0Client client;

    public MutationResolverImpl(Shop0Client client) {
        this.client = client;
    }

    public OrderResponseProjection getOrderResponseProjection() {
        OrderResponseProjection proj = new OrderResponseProjection()
                .id()
                .lineItems(new LineItemConnectionResponseProjection()
                        .pageInfo(new PageInfoResponseProjection().all$())
                        .edges(new LineItemEdgeResponseProjection()
                                .cursor()
                                .node(new LineItemResponseProjection()
                                        .id()
                                        .currentQuantity()
                                        .discountedTotalSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$()))
                                        .discountedUnitPriceSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$()))
                                        .image(new ImageResponseProjection()
                                                .originalSrc()
                                                .transformedSrc()
                                        )
                                        .name()
                                        .originalTotalSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$()))
                                        .originalTotalSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$()))
                                        .product(new ProductResponseProjection()
                                                .id()
                                                .title()
                                        )
                                        .quantity()
                                        .requiresShipping()
                                        .sku()
                                        .title()
                                        .totalDiscountSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$()))
                                        .variant(new ProductVariantResponseProjection()
                                                .id()
                                        )
                                        .variantTitle()
                                        .vendor()
                                        .vendorSku()
                                )
                        )

                )
                .cancelReason()
                .cancelledAt()
                .cartDiscountAmountSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .clientIp()
                .closed()
                .closedAt()
                .createdAt()
                .currencyCode()
                .customer(new CustomerResponseProjection()
                        .id()
                )
                .displayFinancialStatus()
                .displayFulfillmentStatus()
                .email()
                .fulfillable()
                .fulfillments(new FulfillmentResponseProjection()
                        .id()
                        .fulfillmentLineItems(new FulfillmentLineItemConnectionResponseProjection()
                                .pageInfo(new PageInfoResponseProjection().all$())
                                .totalCount()
                                .edges(new FulfillmentLineItemEdgeResponseProjection()
                                        .cursor()
                                        .node(new FulfillmentLineItemResponseProjection()
                                                .id()
                                                .quantity()
                                                .lineItem(new LineItemResponseProjection()
                                                        .id()
                                                        .image(new ImageResponseProjection()
                                                                .originalSrc()
                                                                .transformedSrc()
                                                        )
                                                        .name()
                                                        .product(new ProductResponseProjection()
                                                                .id()
                                                                .title()
                                                        )
                                                        .quantity()
                                                        .requiresShipping()
                                                        .sku()
                                                        .title()
                                                        .variant(new ProductVariantResponseProjection()
                                                                .id()
                                                        )
                                                        .variantTitle()
                                                        .vendor()
                                                        .vendorSku()
                                                )
                                        )
                                )
                        )
                        .createdAt()
                        .deliveredAt()
                        .estimatedDeliveryAt()
                        .inTransitAt()
                        .requiresShipping()
                        .service(new FulfillmentServiceResponseProjection()
                                .handle()
                                .serviceName()
                        )
                        .status()
                        .totalQuantity()
                        .updatedAt()
                )
                .fullyPaid()
                .note()
                .paymentGatewayNames()
                .phone()
                .presentmentCurrencyCode()
                .processedAt()
                .refundable()
                .refunds(new RefundResponseProjection()
                        .id()
                        .refundLineItems(new RefundLineItemConnectionResponseProjection()
                                .pageInfo(new PageInfoResponseProjection().all$())
                                .edges(new RefundLineItemEdgeResponseProjection()
                                        .cursor()
                                        .node(new RefundLineItemResponseProjection()
                                                .lineItem(new LineItemResponseProjection()
                                                        .id()
                                                        .image(new ImageResponseProjection()
                                                                .originalSrc()
                                                                .transformedSrc()
                                                        )
                                                        .name()
                                                        .product(new ProductResponseProjection()
                                                                .id()
                                                                .title()
                                                        )
                                                        .quantity()
                                                        .requiresShipping()
                                                        .sku()
                                                        .title()
                                                        .variant(new ProductVariantResponseProjection()
                                                                .id()
                                                        )
                                                        .variantTitle()
                                                        .vendor()
                                                        .vendorSku()
                                                )
                                                .priceSet(new MoneyBagResponseProjection()
                                                        .presentmentMoney(new MoneyResponseProjection().all$())
                                                        .shopMoney(new MoneyResponseProjection().all$())
                                                )
                                                .quantity()
                                        )
                                )
                        )
                        .createdAt()
                        .note()
                        .reason()
                        .totalRefundedSet(new MoneyBagResponseProjection()
                                .presentmentMoney(new MoneyResponseProjection().all$())
                                .shopMoney(new MoneyResponseProjection().all$()))
                        .updatedAt()
                )
                .requiresShipping()
                .shippingAddress(new MailingAddressResponseProjection().all$())
                .source(new OrderSourceResponseProjection().all$())
                .subtotalLineItemsQuantity()
                .subtotalPriceSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .test()
                .totalDiscountsSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .totalPriceSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .totalReceivedSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .totalRefundedSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .totalShippingPriceSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .totalWeight()
                .unpaid()
                .updatedAt();

        return proj;
    }

    public RefundResponseProjection getRefundResponseProjection() {
        RefundResponseProjection proj = new RefundResponseProjection()
                .id()
                .refundLineItems(new RefundLineItemConnectionResponseProjection()
                        .pageInfo(new PageInfoResponseProjection().all$())
                        .edges(new RefundLineItemEdgeResponseProjection()
                                .cursor()
                                .node(new RefundLineItemResponseProjection()
                                        .lineItem(new LineItemResponseProjection()
                                                .id()
                                                .image(new ImageResponseProjection()
                                                        .originalSrc()
                                                        .transformedSrc()
                                                )
                                                .name()
                                                .product(new ProductResponseProjection()
                                                        .id()
                                                        .title()
                                                )
                                                .quantity()
                                                .requiresShipping()
                                                .sku()
                                                .title()
                                                .variant(new ProductVariantResponseProjection()
                                                        .id()
                                                )
                                                .variantTitle()
                                                .vendor()
                                                .vendorSku()
                                        )
                                        .priceSet(new MoneyBagResponseProjection()
                                                .presentmentMoney(new MoneyResponseProjection().all$())
                                                .shopMoney(new MoneyResponseProjection().all$())
                                        )
                                        .quantity()
                                )
                        )
                )
                .createdAt()
                .note()
                .reason()
                .totalRefundedSet(new MoneyBagResponseProjection()
                        .presentmentMoney(new MoneyResponseProjection().all$())
                        .shopMoney(new MoneyResponseProjection().all$()))
                .updatedAt();

        return proj;
    }

    @Override
    public @NotNull CheckoutCreateTO checkoutCreate(@NotNull CheckoutCreateInputTO input) throws Exception {
        return null;
    }

    @Override
    public @NotNull OrderCloseTO orderClose(@NotNull OrderCloseInputTO input) throws Exception {
        OrderCloseMutationRequest req = OrderCloseMutationRequest.builder().setInput(input).build();
        OrderCloseResponseProjection proj = new OrderCloseResponseProjection()
                .userErrors(new UserErrorResponseProjection().all$())
                .order(getOrderResponseProjection());

        GraphQLRequest graphQLRequest = new GraphQLRequest(req, proj);
        OrderCloseMutationResponse result = this.client.invoke(graphQLRequest, OrderCloseMutationResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }
        return result.orderClose();
    }

    @Override
    public @NotNull RefundCreateTO refundCreate(@NotNull RefundInputTO input) throws Exception {
        RefundCreateMutationRequest req = RefundCreateMutationRequest.builder().setInput(input).build();
        RefundCreateResponseProjection proj = new RefundCreateResponseProjection()
                .order(getOrderResponseProjection())
                .refund(getRefundResponseProjection())
                .userErrors(new UserErrorResponseProjection().all$());

        GraphQLRequest graphQLRequest = new GraphQLRequest(req, proj);
        RefundCreateMutationResponse result = this.client.invoke(graphQLRequest, RefundCreateMutationResponse.class);
        if (result.hasErrors()) {
            throw new Shop0ApiException(result.getErrors());
        }
        return result.refundCreate();
    }

    @Override
    public @NotNull ProductCreateTO productCreate(@NotNull ProductInputTO input) throws Exception {
        return null;
    }

    @Override
    public @NotNull ProductDeleteTO productDelete(@NotNull ProductDeleteInputTO input) throws Exception {
        return null;
    }
}

import io.github.shop0.openapi.graphql.MutationResolverImpl;
import io.github.shop0.openapi.graphql.QueryResolverImpl;
import io.github.shop0.openapi.graphql.Shop0Client;
import io.github.shop0.openapi.graphql.api.MutationResolver;
import io.github.shop0.openapi.graphql.api.QueryResolver;
import io.github.shop0.openapi.graphql.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {
    private Shop0Client shop0Client;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        shop0Client = new Shop0Client.Builder().
                host("https://api.xshop.lucfish.com/admin/v1/graphql").
                debug(true).
                accessToken("").
                skipSSL(true).
                build();
    }

    @Test
    public void testOrder() throws Exception {
        QueryResolver queryResolver = new QueryResolverImpl(shop0Client);
        OrderTO order = queryResolver.order("2e609ec0-2af5-4b69-bc1e-7686a1400cbc");
        System.out.println(order);
    }

    @Test
    public void testOrders() throws Exception {
        QueryResolver queryResolver = new QueryResolverImpl(shop0Client);
        OrderConnectionTO orderConnection = queryResolver.orders(null, null, 100, 0, null, null, null, OrderSortKeysTO.UPDATED_AT);
        System.out.println(orderConnection);
        System.out.println("find size: " + orderConnection.getEdges().size());
    }

    // 关闭订单
    @Test
    public void orderClose() throws Exception {
        String orderId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        OrderCloseInputTO input = new OrderCloseInputTO();
        input.setId(orderId);

        OrderCloseTO order = mutationResolver.orderClose(input);
        System.out.println(order);
    }

    // 更新订单
    @Test
    public void orderUpdate() throws Exception {
        String orderId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        OrderInputTO input = new OrderInputTO();
        input.setId(orderId);
        input.setNote("第1次更新");

        OrderUpdateTO order = mutationResolver.orderUpdate(input);
        System.out.println(order);
    }

    // 创建退款单
    @Test
    public void refundCreate() throws Exception {
        String orderId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        RefundInputTO input = new RefundInputTO();
        input.setOrderId(orderId);

        RefundCreateTO order = mutationResolver.refundCreate(input);
        System.out.println(order);
    }

    // 拒绝退款
    @Test
    public void refundSessionReject() throws Exception {
        String refundSessionId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        RefundSessionRejectionReasonInputTO input = new RefundSessionRejectionReasonInputTO();
        input.setCode(RefundSessionStatusReasonRejectionCodeTO.PROCESSING_ERROR);
        input.setMerchantMessage("拒绝退款");

        RefundSessionRejectTO order = mutationResolver.refundSessionReject(refundSessionId, input);
        System.out.println(order);
    }

    // 同意退款
    @Test
    public void refundSessionResolve() throws Exception {
        String refundSessionId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);

        RefundSessionResolveTO order = mutationResolver.refundSessionResolve(refundSessionId);
        System.out.println(order);
    }

    // 创建物流单
    @Test
    public void fulfillmentCreate() throws Exception {
        String orderId = "";

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        FulfillmentV2InputTO input = new FulfillmentV2InputTO();
        FulfillmentOrderLineItemsInputTO fulfillmentOrderLineItemsInputTO = new FulfillmentOrderLineItemsInputTO();
        List<FulfillmentOrderLineItemsInputTO> items = new ArrayList<>();

        fulfillmentOrderLineItemsInputTO.setFulfillmentOrderId(orderId);
        items.add(fulfillmentOrderLineItemsInputTO);
        input.setLineItemsByFulfillmentOrder(items);

        FulfillmentCreateTO order = mutationResolver.fulfillmentCreate(input, "测试");
        System.out.println(order);
    }

}

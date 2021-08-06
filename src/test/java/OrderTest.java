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
    String host = "https://api.xshop.lucfish.com/admin/v1/graphql";
    String orderId = "EC2021080619553600000097";
    String itemId = "54580828213251";
    String refundSessionId = "202108061750269462484";

    {
        host = "http://localhost:3000/graphql";
    }

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        shop0Client = new Shop0Client.Builder().
                host(host).
                debug(true).
                accessToken("").
                skipSSL(true).
                build();
    }

    @Test
    public void testOrder() throws Exception {
        QueryResolver queryResolver = new QueryResolverImpl(shop0Client);
        OrderTO order = queryResolver.order(orderId);
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

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        OrderCloseInputTO input = new OrderCloseInputTO();
        input.setId(orderId);

        OrderCloseTO order = mutationResolver.orderClose(input);
        System.out.println(order);
    }

    // 更新订单
    @Test
    public void orderUpdate() throws Exception {

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        OrderInputTO input = new OrderInputTO();
        MailingAddressInputTO shippingAddress = new MailingAddressInputTO();
        shippingAddress.setAddress1("湖北");
        shippingAddress.setAddress2("光谷广场");
        shippingAddress.setCity("武汉");
        shippingAddress.setId("54575883653121");
        shippingAddress.setProvince("湖北");
        shippingAddress.setPhone("11111111111");

        input.setId(orderId);
        input.setShippingAddress(shippingAddress);

        OrderUpdateTO order = mutationResolver.orderUpdate(input);
        System.out.println(order);
    }

    // 创建退款单
    @Test
    public void refundCreate() throws Exception {

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);
        RefundInputTO input = new RefundInputTO();
        input.setOrderId(orderId);
        input.setNote("不要了");

        RefundCreateTO order = mutationResolver.refundCreate(input);
        System.out.println(order);
    }

    // 拒绝退款
    @Test
    public void refundSessionReject() throws Exception {

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

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);

        RefundSessionResolveTO order = mutationResolver.refundSessionResolve(refundSessionId);
        System.out.println(order);
    }

    // 创建物流单
    @Test
    public void fulfillmentCreate() throws Exception {

        MutationResolver mutationResolver = new MutationResolverImpl(shop0Client);


        FulfillmentOrderLineItemInputTO fulfillmentOrderLineItemInputTO = new FulfillmentOrderLineItemInputTO();
        fulfillmentOrderLineItemInputTO.setId(itemId);
        fulfillmentOrderLineItemInputTO.setQuantity(1);

        List<FulfillmentOrderLineItemInputTO> fulfillmentOrderLineItems = new ArrayList<>();
        fulfillmentOrderLineItems.add(fulfillmentOrderLineItemInputTO);

        FulfillmentOrderLineItemsInputTO fulfillmentOrderLineItemsInputTO = new FulfillmentOrderLineItemsInputTO();
        fulfillmentOrderLineItemsInputTO.setFulfillmentOrderId(orderId);
        fulfillmentOrderLineItemsInputTO.setFulfillmentOrderLineItems(fulfillmentOrderLineItems);

        List<FulfillmentOrderLineItemsInputTO> items = new ArrayList<>();
        items.add(fulfillmentOrderLineItemsInputTO);

        FulfillmentV2InputTO input = new FulfillmentV2InputTO();
        input.setLineItemsByFulfillmentOrder(items);

        FulfillmentTrackingInputTO fulfillmentTrackingInputTO = new FulfillmentTrackingInputTO();
        fulfillmentTrackingInputTO.setCompany("顺丰");
        fulfillmentTrackingInputTO.setNumber("111111111");
        input.setTrackingInfo(fulfillmentTrackingInputTO);

        FulfillmentCreateTO order = mutationResolver.fulfillmentCreate(input, "测试");
        System.out.println(order);
    }

}

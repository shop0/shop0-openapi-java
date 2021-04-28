import io.github.shop0.openapi.graphql.QueryResolverImpl;
import io.github.shop0.openapi.graphql.Shop0Client;
import io.github.shop0.openapi.graphql.api.QueryResolver;
import io.github.shop0.openapi.graphql.model.OrderConnectionTO;
import io.github.shop0.openapi.graphql.model.OrderSortKeysTO;
import io.github.shop0.openapi.graphql.model.OrderTO;
import org.junit.jupiter.api.Test;

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

}

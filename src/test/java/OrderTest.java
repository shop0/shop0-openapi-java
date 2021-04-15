import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.shop0.openapi.graphql.api.QueryResolver;
import com.shop0.openapi.graphql.model.*;
import com.shop0.openapi.graphql.QueryResolverImpl;
import com.shop0.openapi.graphql.Shop0ApiException;
import com.shop0.openapi.graphql.Shop0Client;
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

        OrderResponseProjection proj = new OrderResponseProjection().id().totalWeight();
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

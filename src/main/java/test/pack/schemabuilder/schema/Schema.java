package test.pack.schemabuilder.schema;

import test.pack.schemabuilder.schema.columns.Columns;
import test.pack.schemabuilder.schema.fields.Fields;
import test.pack.schemabuilder.schema.joinhints.JoinHints;
import test.pack.schemabuilder.schema.options.Options;
import test.pack.schemabuilder.schema.order.Order;
import test.pack.schemabuilder.schema.orderhints.OrderHints;
import test.pack.schemabuilder.schema.search.Search;
import test.pack.schemabuilder.schema.validators.Validators;

public class Schema {
    private Fields fields;
    private Columns columns;
    private Validators validators;
    private Search search;
    private Order order;
    private OrderHints orderHints;
    private JoinHints joinHints;
    private Options options;

    public Fields getFields() {
        return fields;
    }

    public Schema setFields(Fields fields) {
        this.fields = fields;
        return this;
    }

    public Columns getColumns() {
        return columns;
    }

    public Schema setColumns(Columns columns) {
        this.columns = columns;
        return this;
    }

    public Validators getValidators() {
        return validators;
    }

    public Schema setValidators(Validators validators) {
        this.validators = validators;
        return this;
    }

    public Search getSearch() {
        return search;
    }

    public Schema setSearch(Search search) {
        this.search = search;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Schema setOrder(Order order) {
        this.order = order;
        return this;
    }

    public OrderHints getOrderHints() {
        return orderHints;
    }

    public Schema setOrderHints(OrderHints orderHints) {
        this.orderHints = orderHints;
        return this;
    }

    public JoinHints getJoinHints() {
        return joinHints;
    }

    public Schema setJoinHints(JoinHints joinHints) {
        this.joinHints = joinHints;
        return this;
    }

    public Options getOptions() {
        return options;
    }

    public Schema setOptions(Options options) {
        this.options = options;
        return this;
    }
}

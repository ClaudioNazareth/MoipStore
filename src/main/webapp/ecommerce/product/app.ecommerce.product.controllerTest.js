describe("A suite", function() {
    beforeEach(module('moipstore.ecommerce.product'));

    beforeEach(inject(function(_$controller_){
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $controller = _$controller_;
    }));

    it("contains spec with an expectation", function() {
        var controller = $controller('EcommerceProductController', { $scope: $scope });
        expect(controller).toBeDefined();
    });
});
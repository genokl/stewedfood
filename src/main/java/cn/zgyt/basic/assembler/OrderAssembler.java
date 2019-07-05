//package cn.zgyt.basic.assembler;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//import java.util.Arrays;
//
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
//
//import cn.zgyt.basic.resource.OrderResource;
//import cn.zgyt.controller.OrderController;
//
//
//public class OrderAssembler extends ResourceAssemblerSupport<OrderAssembler, OrderResource> {
//
//
//		public OrderAssembler() {
//			super(OrderController.class, OrderResource.class);
//		}
//
//		@Override
//		public OrderResource toResource(OrderAssembler entity) {
////			Link org = linkTo(methodOn(BaseOrgController.class).lengthLess(8)).withRel("_org");
//			Link self = linkTo(methodOn(OrderController.class).self(entity.getId())).withRel("_self");
//			Link delete = linkTo(methodOn(OrderController.class).delete(entity.getId())).withRel("_delete");
//			Link update = linkTo(methodOn(OrderController.class).update(null,null,null)).withRel("_update");
//			return new OrderResource(entity, Arrays.asList(self, update, delete));
//		}
//
//		
//}

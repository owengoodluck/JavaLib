/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Orders
 * API Version: 2013-09-01
 * Library Version: 2015-09-24
 * Generated: Fri Sep 25 20:06:20 GMT 2015
 */
package com.amazonservices.mws.orders._2013_09_01.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrders;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsByNextTokenResult;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResult;
import com.amazonservices.mws.orders._2013_09_01.model.OrderItem;
import com.amazonservices.mws.orders._2013_09_01.model.ResponseHeaderMetadata;
import com.amazonservices.mws.orders._2013_09_01.samples.MarketplaceWebServiceOrdersSampleConfig;
import com.owen.wms.common.constant.AppConstant;


/** Sample call for ListOrderItems. */
public class ListOrderItemsService {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    private static ListOrderItemsResponse invokeListOrderItems(
            MarketplaceWebServiceOrders client, 
            ListOrderItemsRequest request) {
        try {
            // Call the service.
            ListOrderItemsResponse response = client.listOrderItems(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }

    /**
     *  Command line entry point.
     */
    public static void main(String[] args) {
    	listOrderItems("002-2562143-3058646");
    }
    
    public static List<OrderItem> listOrderItems(String amazonOrderId) {
    	List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceOrdersSampleConfig.
        MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersSampleConfig.getClient();

        // Create a request.
        ListOrderItemsRequest request = new ListOrderItemsRequest();
        request.setSellerId(AppConstant.sellerId);
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        request.setAmazonOrderId(amazonOrderId);

        // Make the call.
        ListOrderItemsResponse listOrderItemsResponse = ListOrderItemsService.invokeListOrderItems(client, request);
        ListOrderItemsResult result = listOrderItemsResponse.getListOrderItemsResult();
        List<OrderItem> orderItems = result.getOrderItems();
        orderItemList.addAll(orderItems);
        
        //get next pages if exist
        String nextToken = result.getNextToken();
        ListOrderItemsByNextTokenResult ListOrderItemsByNextTokenResult=null;
        while(nextToken!=null && nextToken.trim().length()>0){
        	ListOrderItemsByNextTokenResult = ListOrderItemsByNextTokenService.listOrderItemsByNextToken(nextToken);
        	orderItemList.addAll(ListOrderItemsByNextTokenResult.getOrderItems());
        	nextToken = ListOrderItemsByNextTokenResult.getNextToken();
        }
        return orderItemList;
    }

}

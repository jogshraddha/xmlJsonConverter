package com.project.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESClient {
	@SuppressWarnings("unchecked")
	public static TransportClient getTransportClient(String host, int port) {
		Settings settings = Settings.builder()
		        .put("cluster.name", "vistalytics").put("client.transport.sniff", true).build();
		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(settings).addTransportAddress(
					new InetSocketTransportAddress(InetAddress.getLocalHost(), 9300));
			if(client.connectedNodes() == null){
	            System.err.println("No connected nodes found.");
	        }else{
	            for(DiscoveryNode d : client.connectedNodes()){
	                System.err.println("DiscoveryNode: " + d.getHostName());
	            }
	            for(DiscoveryNode d : client.listedNodes()){
	                System.err.println("Node: " + d.getHostName());
	            }
	        }
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
		
    }

    public static IndexResponse doIndex(Client client, String index, String type, String id, Map<String, Object> data) {

        return client.prepareIndex(index, type, id)
                .setSource(data)
                .execute()
                .actionGet();
    }
}

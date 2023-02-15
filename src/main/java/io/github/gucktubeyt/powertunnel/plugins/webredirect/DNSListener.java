package io.github.gucktubeyt.powertunnel.plugins.webredirect;

import io.github.krlvm.powertunnel.sdk.proxy.DNSRequest;
import io.github.krlvm.powertunnel.sdk.proxy.ProxyAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.util.Map;

public class DNSListener extends ProxyAdapter {
    public String address;
    public String[] URLWeb;
    public int portAddress;

    public DNSListener(String[] web, String addr, int port) {
        URLWeb = web;
        address = addr;
        portAddress = port;
    }

    public Boolean onResolutionRequest(@NotNull DNSRequest request) {
        for (int a = 0; a < URLWeb.length; a++) {
            if (request.getHost().endsWith(URLWeb[a])) {
                request.setResponse(new InetSocketAddress(address, portAddress));
                System.out.println("found!\n");
                return super.onResolutionRequest(request);
            }
        }
        System.out.println("not found");
        return super.onResolutionRequest(request);
    }
}

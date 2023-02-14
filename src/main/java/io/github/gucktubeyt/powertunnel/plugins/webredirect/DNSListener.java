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
    public String URLWeb;
    public int portAddress;

    public DNSListener(String web, String addr, int port) {
        address = addr;
        portAddress = port;
        URLWeb = web;
    }
    
    public Boolean onResolutionRequest(@NotNull DNSRequest request) {
        if (request.getHost().contains(URLWeb)) request.setResponse(new InetSocketAddress(address, portAddress));
        return super.onResolutionRequest(request);
    }
}

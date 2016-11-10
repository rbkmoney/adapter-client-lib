package com.rbkmoney.hellgate.client.party.management;

import com.rbkmoney.damsel.domain.ShopAccountSet;
import com.rbkmoney.damsel.payment_processing.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class HellgateClientPartyManagementTest {

    @Mock
    HellgateClientPartyManagement client;

    @Mock
    PartyState partyState;

    @Mock
    UserInfo userInfo;

    @Mock
    ShopParams shopParams;

    @Mock
    ShopState shopState;

    @Mock
    ShopUpdate shopUpdate;

    @Mock
    ClaimResult claimResult;

    @Mock
    Claim claim;

    @Mock
    EventRange eventRange;

    @Mock
    ShopAccountSet shopAccountSet;

    @Mock
    ShopAccountState shopAccountState;

    private String partyId = "partyId";
    private String shopId = "shopId";
    private String reason = "reason";

    private List<Event> eventList = Collections.EMPTY_LIST;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get() throws Exception {
        Mockito.when(client.get(userInfo, partyId)).thenReturn(partyState);

        assertEquals(partyState, client.get(userInfo, partyId));
    }

    @Test
    public void createShop() throws Exception {
        Mockito.when(client.createShop(userInfo, partyId, shopParams)).thenReturn(claimResult);

        assertEquals(claimResult, client.createShop(userInfo, partyId, shopParams));
    }

    @Test
    public void getShop() throws Exception {
        Mockito.when(client.getShop(userInfo, partyId, shopId)).thenReturn(shopState);

        assertEquals(shopState, client.getShop(userInfo, partyId, shopId));
    }

    @Test
    public void updateShop() throws Exception {
        Mockito.when(client.updateShop(userInfo, partyId, shopId, shopUpdate)).thenReturn(claimResult);

        assertEquals(claimResult, client.updateShop(userInfo, partyId, shopId, shopUpdate));
    }

    @Test
    public void getClaim() throws Exception {
        String claimId = "claimId";
        Mockito.when(client.getClaim(userInfo, partyId, claimId)).thenReturn(claim);

        assertEquals(claim, client.getClaim(userInfo, partyId, claimId));
    }

    @Test
    public void getPendingClaim() throws Exception {
        Mockito.when(client.getPendingClaim(userInfo, partyId)).thenReturn(claim);

        assertEquals(claim, client.getPendingClaim(userInfo, partyId));
    }

    @Test
    public void suspend() throws Exception {
        Mockito.when(client.suspend(userInfo, partyId)).thenReturn(claimResult);

        assertEquals(claimResult, client.suspend(userInfo, partyId));
    }

    @Test
    public void activate() throws Exception {
        Mockito.when(client.activate(userInfo, partyId)).thenReturn(claimResult);

        assertEquals(claimResult, client.activate(userInfo, partyId));
    }

    @Test
    public void block() throws Exception {
        Mockito.when(client.block(userInfo, partyId, reason)).thenReturn(claimResult);

        assertEquals(claimResult, client.block(userInfo, partyId, reason));
    }

    @Test
    public void unblock() throws Exception {
        Mockito.when(client.unblock(userInfo, partyId, reason)).thenReturn(claimResult);

        assertEquals(claimResult, client.unblock(userInfo, partyId, reason));
    }

    @Test
    public void suspendShop() throws Exception {
        Mockito.when(client.suspendShop(userInfo, partyId, shopId)).thenReturn(claimResult);

        assertEquals(claimResult, client.suspendShop(userInfo, partyId, shopId));
    }

    @Test
    public void activateShop() throws Exception {
        Mockito.when(client.activateShop(userInfo, partyId, shopId)).thenReturn(claimResult);

        assertEquals(claimResult, client.activateShop(userInfo, partyId, shopId));
    }

    @Test
    public void blockShop() throws Exception {
        Mockito.when(client.blockShop(userInfo, partyId, shopId, reason)).thenReturn(claimResult);

        assertEquals(claimResult, client.blockShop(userInfo, partyId, shopId, reason));
    }

    @Test
    public void unblockShop() throws Exception {
        Mockito.when(client.unblockShop(userInfo, partyId, shopId, reason)).thenReturn(claimResult);

        assertEquals(claimResult, client.unblockShop(userInfo, partyId, shopId, reason));
    }

    @Test
    public void getEvents() throws Exception {
        Mockito.when(client.getEvents(userInfo, partyId, eventRange)).thenReturn(eventList);

        assertEquals(eventList, client.getEvents(userInfo, partyId, eventRange));
    }

    @Test
    public void getShopAccountState() throws Exception {
        long accountId = 1L;
        Mockito.when(client.getShopAccountState(userInfo, partyId, accountId)).thenReturn(shopAccountState);

        assertEquals(shopAccountState, client.getShopAccountState(userInfo, partyId, accountId));
    }

    @Test
    public void getShopAccountSet() throws Exception {
        Mockito.when(client.getShopAccountSet(userInfo, partyId, shopId)).thenReturn(shopAccountSet);

        assertEquals(shopAccountSet, client.getShopAccountSet(userInfo, partyId, shopId));
    }

}
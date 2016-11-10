package com.rbkmoney.hellgate.client.party.management;

import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.domain.ShopAccountSet;
import com.rbkmoney.damsel.payment_processing.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HellgateClientPartyManagement implements PartyManagementSrv.Iface {

    private final static Logger LOGGER = LoggerFactory.getLogger(HellgateClientPartyManagement.class);

    @Autowired
    private PartyManagementSrv.Iface partyManagement;

    @Override
    public void create(UserInfo userInfo, String party_id) throws InvalidUser, PartyExists, TException {
        LOGGER.info("Hellgate PartyManagement: createParty start");
        partyManagement.create(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: createParty finish");
    }

    @Override
    public PartyState get(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getPartyState start");
        PartyState partyState = partyManagement.get(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: getPartyState finish");
        return partyState;
    }

    @Override
    public ClaimResult createShop(UserInfo userInfo, String party_id, ShopParams shopParams) throws InvalidUser, PartyNotFound, InvalidPartyStatus, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: createShop start");
        ClaimResult response = partyManagement.createShop(userInfo, party_id, shopParams);
        LOGGER.info("Hellgate PartyManagement: createShop finish");
        return response;
    }

    @Override
    public ShopState getShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getShop start");
        ShopState response = partyManagement.getShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: getShop finish");
        return response;
    }

    @Override
    public ClaimResult updateShop(UserInfo userInfo, String party_id, String shop_id, ShopUpdate shopUpdate) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidPartyStatus, InvalidShopStatus, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: createShop start");
        ClaimResult response = partyManagement.updateShop(userInfo, party_id, shop_id, shopUpdate);
        LOGGER.info("Hellgate PartyManagement: createShop finish");
        return response;
    }

    @Override
    public Claim getClaim(UserInfo userInfo, String party_id, String claim_id) throws InvalidUser, PartyNotFound, ClaimNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getClaim start");
        Claim response = partyManagement.getClaim(userInfo, party_id, claim_id);
        LOGGER.info("Hellgate PartyManagement: getClaim finish");
        return response;
    }

    @Override
    public Claim getPendingClaim(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, ClaimNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getPendingClaim start");
        Claim response = partyManagement.getPendingClaim(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: getPendingClaim finish");
        return response;
    }

    @Override
    public void acceptClaim(UserInfo userInfo, String party_id, String claim_id) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: acceptClaim start");
        partyManagement.acceptClaim(userInfo, party_id, claim_id);
        LOGGER.info("Hellgate PartyManagement: acceptClaim finish");
    }

    @Override
    public void denyClaim(UserInfo userInfo, String party_id, String claim_id, String reason) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: denyClaim start");
        partyManagement.denyClaim(userInfo, party_id, claim_id, reason);
        LOGGER.info("Hellgate PartyManagement: denyClaim finish");
    }

    @Override
    public void revokeClaim(UserInfo userInfo, String party_id, String claim_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: revokeClaim start");
        partyManagement.revokeClaim(userInfo, party_id, claim_id, reason);
        LOGGER.info("Hellgate PartyManagement: revokeClaim finish");
    }

    @Override
    public ClaimResult suspend(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: suspend start");
        ClaimResult response = partyManagement.suspend(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: suspend finish");
        return response;
    }

    @Override
    public ClaimResult activate(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: activate start");
        ClaimResult response = partyManagement.activate(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: activate finish");
        return response;
    }

    @Override
    public ClaimResult block(UserInfo userInfo, String party_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: block start");
        ClaimResult response = partyManagement.block(userInfo, party_id, reason);
        LOGGER.info("Hellgate PartyManagement: block finish");
        return response;
    }

    @Override
    public ClaimResult unblock(UserInfo userInfo, String party_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: unblock start");
        ClaimResult response = partyManagement.unblock(userInfo, party_id, reason);
        LOGGER.info("Hellgate PartyManagement: unblock finish");
        return response;
    }

    @Override
    public ClaimResult suspendShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: suspendShop start");
        ClaimResult response = partyManagement.suspendShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: suspendShop finish");
        return response;
    }

    @Override
    public ClaimResult activateShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: activateShop start");
        ClaimResult response = partyManagement.activateShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: activateShop finish");
        return response;
    }

    @Override
    public ClaimResult blockShop(UserInfo userInfo, String party_id, String shop_id, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: blockShop start");
        ClaimResult response = partyManagement.blockShop(userInfo, party_id, shop_id, reason);
        LOGGER.info("Hellgate PartyManagement: blockShop finish");
        return response;
    }

    @Override
    public ClaimResult unblockShop(UserInfo userInfo, String party_id, String shop_id, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: unblockShop start");
        ClaimResult response = partyManagement.unblockShop(userInfo, party_id, shop_id, reason);
        LOGGER.info("Hellgate PartyManagement: unblockShop finish");
        return response;
    }

    @Override
    public List<Event> getEvents(UserInfo userInfo, String party_id, EventRange eventRange) throws InvalidUser, PartyNotFound, EventNotFound, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: getEvents start");
        List<Event> response = partyManagement.getEvents(userInfo, party_id, eventRange);
        LOGGER.info("Hellgate PartyManagement: getEvents finish");
        return response;
    }

    @Override
    public ShopAccountState getShopAccountState(UserInfo userInfo, String party_id, long account_id) throws InvalidUser, PartyNotFound, AccountNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getShopAccountState start");
        ShopAccountState response = partyManagement.getShopAccountState(userInfo, party_id, account_id);
        LOGGER.info("Hellgate PartyManagement: getShopAccountState finish");
        return response;
    }

    @Override
    public ShopAccountSet getShopAccountSet(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, AccountSetNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: ShopAccountSet start");
        ShopAccountSet response = partyManagement.getShopAccountSet(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: ShopAccountSet finish");
        return response;
    }
}

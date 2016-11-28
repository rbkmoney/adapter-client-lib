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
        LOGGER.info("Hellgate PartyManagement: createParty start with userInfo {}, party_id {}",
                userInfo, party_id
        );
        partyManagement.create(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: createParty finish");
    }

    @Override
    public PartyState get(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getPartyState start with userInfo {}, party_id {}",
                userInfo, party_id
        );
        PartyState partyState = partyManagement.get(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: getPartyState finish with partyState {}", partyState);
        return partyState;
    }

    @Override
    public ClaimResult createShop(UserInfo userInfo, String party_id, ShopParams shopParams) throws InvalidUser, PartyNotFound, InvalidPartyStatus, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: createShop start with userInfo {}, party_id {}, shopParams {}",
                userInfo, party_id, shopParams
        );
        ClaimResult claimResult = partyManagement.createShop(userInfo, party_id, shopParams);
        LOGGER.info("Hellgate PartyManagement: createShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ShopState getShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getShop start with userInfo {}, party_id {}, shop_id {}",
                userInfo, party_id, shop_id
        );
        ShopState shopState = partyManagement.getShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: getShop finish with shopState {}", shopState);
        return shopState;
    }

    @Override
    public ClaimResult updateShop(UserInfo userInfo, String party_id, String shop_id, ShopUpdate shopUpdate) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidPartyStatus, InvalidShopStatus, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: createShop start with userInfo {}, party_id {}, shop_id {}, shopUpdate {}",
                userInfo, party_id, shop_id, shopUpdate
                );
        ClaimResult claimResult = partyManagement.updateShop(userInfo, party_id, shop_id, shopUpdate);
        LOGGER.info("Hellgate PartyManagement: createShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public Claim getClaim(UserInfo userInfo, String party_id, String claim_id) throws InvalidUser, PartyNotFound, ClaimNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getClaim start with userInfo {}, party_id {}, claim_id {}",
                userInfo, party_id, claim_id
        );
        Claim claim = partyManagement.getClaim(userInfo, party_id, claim_id);
        LOGGER.info("Hellgate PartyManagement: getClaim finish with claim {}", claim);
        return claim;
    }

    @Override
    public Claim getPendingClaim(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, ClaimNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getPendingClaim start with userInfo {}, party_id {}",
                userInfo, party_id
        );
        Claim claim = partyManagement.getPendingClaim(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: getPendingClaim finish with claim {}", claim);
        return claim;
    }

    @Override
    public void acceptClaim(UserInfo userInfo, String party_id, String claim_id) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: acceptClaim start with userInfo {}, party_id {}, claim_id {}",
                userInfo, party_id, claim_id
        );
        partyManagement.acceptClaim(userInfo, party_id, claim_id);
        LOGGER.info("Hellgate PartyManagement: acceptClaim finish");
    }

    @Override
    public void denyClaim(UserInfo userInfo, String party_id, String claim_id, String reason) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: denyClaim start with userInfo {}, party_id {}, claim_id {}, reason {}",
                userInfo, party_id, claim_id, reason
        );
        partyManagement.denyClaim(userInfo, party_id, claim_id, reason);
        LOGGER.info("Hellgate PartyManagement: denyClaim finish");
    }

    @Override
    public void revokeClaim(UserInfo userInfo, String party_id, String claim_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, ClaimNotFound, InvalidClaimStatus, TException {
        LOGGER.info("Hellgate PartyManagement: revokeClaim start with userInfo {}, party_id {}, claim_id {}, reason {}",
                userInfo, party_id, claim_id, reason
        );
        partyManagement.revokeClaim(userInfo, party_id, claim_id, reason);
        LOGGER.info("Hellgate PartyManagement: revokeClaim finish");
    }

    @Override
    public ClaimResult suspend(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: suspend start with userInfo {}, party_id {}",
                userInfo, party_id
        );
        ClaimResult claimResult = partyManagement.suspend(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: suspend finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult activate(UserInfo userInfo, String party_id) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: activate start with userInfo {}, party_id {}",
                userInfo, party_id
        );
        ClaimResult claimResult = partyManagement.activate(userInfo, party_id);
        LOGGER.info("Hellgate PartyManagement: activate finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult block(UserInfo userInfo, String party_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: block start with userInfo {}, party_id {}, reason {}",
                userInfo, party_id, reason
        );
        ClaimResult claimResult = partyManagement.block(userInfo, party_id, reason);
        LOGGER.info("Hellgate PartyManagement: block finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult unblock(UserInfo userInfo, String party_id, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        LOGGER.info("Hellgate PartyManagement: unblock start with userInfo {}, party_id {}, reason {}",
                userInfo, party_id, reason
        );
        ClaimResult claimResult = partyManagement.unblock(userInfo, party_id, reason);
        LOGGER.info("Hellgate PartyManagement: unblock finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult suspendShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: suspendShop start with userInfo {}, party_id {}, shop_id {}",
                userInfo, party_id, shop_id
        );
        ClaimResult claimResult = partyManagement.suspendShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: suspendShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult activateShop(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: activateShop start with userInfo {}, party_id {}, shop_id {}",
                userInfo, party_id, shop_id
        );
        ClaimResult claimResult = partyManagement.activateShop(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: activateShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult blockShop(UserInfo userInfo, String party_id, String shop_id, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: blockShop start with userInfo {}, party_id {}, shop_id {}, reason {}",
                userInfo, party_id, shop_id, reason
        );
        ClaimResult claimResult = partyManagement.blockShop(userInfo, party_id, shop_id, reason);
        LOGGER.info("Hellgate PartyManagement: blockShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public ClaimResult unblockShop(UserInfo userInfo, String party_id, String shop_id, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        LOGGER.info("Hellgate PartyManagement: unblockShop start with userInfo {}, party_id {}, shop_id {}, reason {}",
                userInfo, party_id, shop_id, reason
        );
        ClaimResult claimResult = partyManagement.unblockShop(userInfo, party_id, shop_id, reason);
        LOGGER.info("Hellgate PartyManagement: unblockShop finish with claimResult {}", claimResult);
        return claimResult;
    }

    @Override
    public List<Event> getEvents(UserInfo userInfo, String party_id, EventRange eventRange) throws InvalidUser, PartyNotFound, EventNotFound, InvalidRequest, TException {
        LOGGER.info("Hellgate PartyManagement: getEvents start with userInfo {}, party_id {}, shop_id {}, eventRange {}",
                userInfo, party_id, eventRange
        );
        List<Event> events = partyManagement.getEvents(userInfo, party_id, eventRange);
        LOGGER.info("Hellgate PartyManagement: getEvents finish");
        return events;
    }

    @Override
    public ShopAccountState getShopAccountState(UserInfo userInfo, String party_id, long account_id) throws InvalidUser, PartyNotFound, AccountNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: getShopAccountState start with userInfo {}, party_id {}, account_id {}",
                userInfo, party_id, account_id
        );
        ShopAccountState shopAccountState = partyManagement.getShopAccountState(userInfo, party_id, account_id);
        LOGGER.info("Hellgate PartyManagement: getShopAccountState finish");
        return shopAccountState;
    }

    @Override
    public ShopAccountSet getShopAccountSet(UserInfo userInfo, String party_id, String shop_id) throws InvalidUser, PartyNotFound, ShopNotFound, AccountSetNotFound, TException {
        LOGGER.info("Hellgate PartyManagement: ShopAccountSet start with userInfo {}, party_id {}, shop_id {}",
                userInfo, party_id, shop_id
        );
        ShopAccountSet shopAccountSet = partyManagement.getShopAccountSet(userInfo, party_id, shop_id);
        LOGGER.info("Hellgate PartyManagement: ShopAccountSet finish");
        return shopAccountSet;
    }
}

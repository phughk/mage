
package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.SacrificeCostManaValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledArtifactPermanent;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetControlledPermanent;

import java.util.UUID;

/**
 * @author jeffwadsworth
 */
public final class BoshIronGolem extends CardImpl {

    public BoshIronGolem(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{8}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.GOLEM);

        this.power = new MageInt(6);
        this.toughness = new MageInt(7);

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // {3}{R}, Sacrifice an artifact: Bosh, Iron Golem deals damage equal to the sacrificed artifact's converted mana cost to any target.
        Effect effect = new DamageTargetEffect(SacrificeCostManaValue.ARTIFACT);
        effect.setText("{this} deals damage equal to the sacrificed artifact's mana value to any target");
        Ability ability = new SimpleActivatedAbility(effect, new ManaCostsImpl<>("{3}{R}"));
        ability.addCost(new SacrificeTargetCost(StaticFilters.FILTER_PERMANENT_ARTIFACT));
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);
    }

    private BoshIronGolem(final BoshIronGolem card) {
        super(card);
    }

    @Override
    public BoshIronGolem copy() {
        return new BoshIronGolem(this);
    }
}
